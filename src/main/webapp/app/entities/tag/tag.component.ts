import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITag } from '@/shared/model/tag.model';

import TagService from './tag.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Tag extends Vue {
  @Inject('tagService') private tagService: () => TagService;
  private removeId: number = null;

  public tags: ITag[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTags();
  }

  public clear(): void {
    this.retrieveAllTags();
  }

  public retrieveAllTags(): void {
    this.isFetching = true;

    this.tagService()
      .retrieve()
      .then(
        res => {
          this.tags = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ITag): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTag(): void {
    this.tagService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('myApp.tag.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTags();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
